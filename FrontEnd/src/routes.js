import React from 'react';
import { Navigate, useRoutes } from 'react-router-dom';

// layouts
import DashboardLayout from './layouts/dashboard';
import SimpleLayout from './layouts/simple';
//
import BlogPage from './pages/BlogPage';
import TeacherPage from './pages/teacher/TeacherPage';
import LoginPage from './pages/LoginPage';
import Page404 from './pages/Page404';
import ProductsPage from './pages/ProductsPage';
import DashboardAppPage from './pages/DashboardAppPage';
import NewTeacher from './pages/teacher/NewTeacher';
import StudentPage from './pages/student/StudentPage';
import NewStudent from './pages/student/NewStudent';
import UpdateTeacher from './pages/teacher/UpdateTeacher';
import UpdateStudent from './pages/student/UpdateStudent';
import ProtectedRoute from './shared/ProtectedRoute';



// // ----------------------------------------------------------------------





export default function Router() {

  const adminDefaultPath = "/dashboard/app";
  const teacherDefaultPath = "/dashboard/student";
  const studentDefaultPath = "/dashboard/blog";
  const routes = useRoutes([
    {
      path: "login",
      element: <LoginPage />,
    },
    {
      path: "dashboard",
      element: (
        <ProtectedRoute
          roles={"ADMIN"}
        // defaultPath={adminDefaultPath}
        >
          <DashboardLayout>
            <Navigate to={adminDefaultPath} replace />
          </DashboardLayout>
        </ProtectedRoute>
      ),
      children: [
        { path: "app", element: <DashboardAppPage /> },
        { path: "teacher", element: <TeacherPage /> },
        { path: "teacherNew", element: <NewTeacher /> },
        { path: "teacherUpdate/:id", element: <UpdateTeacher /> },
      ],
    },
    {
      path: "dashboard",
      element: (
        <ProtectedRoute
          roles={"TEACHER"}
        // defaultPath={teacherDefaultPath}
        >
          <DashboardLayout>
            <Navigate to={teacherDefaultPath} replace />
          </DashboardLayout>
        </ProtectedRoute>
      ),
      children: [
        { path: "student", element: <StudentPage /> },
        { path: "studentUpdate/:id", element: <UpdateStudent /> },
        { path: "studentNew", element: <NewStudent /> },
      ],
    },
    
    {
      path: "dashboard",
      element: (
        <ProtectedRoute
          roles={'STUDENT'}
        >
          <DashboardLayout>
            <Navigate to={studentDefaultPath} replace />
          </DashboardLayout>
        </ProtectedRoute>
      ),
      children: [
        { path: "products", element: <ProductsPage /> },
        { path: "blog", element: <BlogPage /> },
      ],
    },

    {
      element: <SimpleLayout />,
      children: [
        { path: "404", element: <Page404 /> },
        { path: "*", element: <Navigate to="/404" replace /> },
      ],
    },
    {
      path: "*",
      element: <Navigate to="/404" replace />,
    },
  ]);

  return routes;
};