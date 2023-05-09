import jwtDecode from "jwt-decode";
import {
    createContext,
    useContext,
    useEffect,
    useState
} from "react";
import { login as performLogin } from "../services/client";




const AuthContext = createContext({});

const AuthProvider = ({children}) => {

    const [user, setUser] = useState(null);

    const isUserAuthenticated = () => {
        console.log("isUserAuthenticated")
        const token = localStorage.getItem("access_token");
        
        if (!token) {
            console.log("user not authenticated", token)
            return false;
        }

        if(Date.now() > jwtDecode(token).exp * 1000) {  
            logout()
            return false;
        }
        
        console.log("user authenticated", token)
       
        return true;
    };

    const logout = () => {
        localStorage.removeItem("access_token");
        setUser(null)
    }

    
    const login = async (credentials) => {
        return new Promise((resolve, reject) => {
            performLogin(credentials)
                .then(response => {
                    const jwtToken = response.headers.authorization;
                    localStorage.setItem('access_token', jwtToken);
                    setUser({...response.data.userDTO});

                    resolve();
                })
                .catch(error => {
                    reject(error);
                });
                
        });
    };
    
    const getScopes = () => {
        const token = localStorage.getItem("access_token");
        if (!token) {
          return [];
        }
        const decodedToken = jwtDecode(token);
        if (!decodedToken || !decodedToken.scopes) {
          return [];
        }
        return decodedToken.scopes;
      };
      

    useEffect(() => {
        let token = localStorage.getItem("access_token");
        if (token) {
            token = jwtDecode(token)
            setUser(token)
        }
    }, [])

    return (
        <AuthContext.Provider value={{
            user,
            isUserAuthenticated,
            getScopes,
            logout,
            login
        }}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => useContext(AuthContext);

export default AuthProvider;