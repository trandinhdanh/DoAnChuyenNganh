import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
// import { is } from "date-fns/locale";


function ProtectedRoute({ roles, children }) {
    const { isUserAuthenticated } = useAuth()
    const { getScopes } = useAuth();

    const scopes = getScopes();

    const navigate = useNavigate();


    useEffect(() => {
        if (!isUserAuthenticated()) {
            navigate("/login");
        }

    }, [isUserAuthenticated, scopes,navigate, roles]);

    
    console.log("roles", scopes);

    return isUserAuthenticated() && scopes.includes(roles) ? children : navigate("/404");
}

export default ProtectedRoute;