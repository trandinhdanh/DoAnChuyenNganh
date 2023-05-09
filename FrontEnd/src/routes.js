import React from 'react';
import { Navigate, useRoutes } from 'react-router-dom';

// layouts
import DashboardLayout from './layouts/dashboard';
import SimpleLayout from './layouts/simple';
//
import BlogPage from './pages/BlogPage';
import TeacherPage from './pages/TeacherPage';
import LoginPage from './pages/LoginPage';
import Page404 from './pages/Page404';
import ProductsPage from './pages/ProductsPage';
import DashboardAppPage from './pages/DashboardAppPage';
import NewTeacher from './pages/NewTeacher';
import StudentPage from './pages/StudentPage';
import NewStudent from './pages/NewStudent';
import UpdateTeacher from './pages/UpdateTeacher';
import UpdateStudent from './pages/UpdateStudent';
import ProtectedRoute from './shared/ProtectedRoute';


// // ----------------------------------------------------------------------


export default function Router() {
  const routes = useRoutes([
    {
      element: <LoginPage />,
      children: [
        // { path: '/' },
        { path: 'login' },
      ],
    },
    // {
    //   path: 'dashboard',
    //   element: (
    //     <ProtectedRoute
    //       element={<DashboardLayout />}
    //       children={[
    //         { element: <Navigate to="/dashboard/app" />, index: true },
    //         { path: 'app', element: <DashboardAppPage /> },
    //         { path: 'teacher', element: <UserPage /> },
    //         { path: 'student', element: <StudentPage /> },
    //         { path: 'teacherNew', element: <NewTeacher /> },
    //         { path: 'teacherUpdate/:id', element: <UpdateTeacher /> },
    //         { path: 'studentUpdate/:id', element: <UpdateStudent /> },
    //         { path: 'studentNew', element: <NewStudent /> },
    //         { path: 'products', element: <ProductsPage /> },
    //         { path: 'blog', element: <BlogPage /> },
    //       ]}
    //     />
    //   ),
    // },
    {
      path: 'dashboard',
      element:  (
        <ProtectedRoute>
          <DashboardLayout>
            <Navigate to="/dashboard/app" replace />
          </DashboardLayout>
        </ProtectedRoute>
      ),
      children: [
        { path: 'app', element: <DashboardAppPage /> },
        { path: 'teacher', element: <TeacherPage /> },
        { path: 'student', element: <StudentPage /> },
        { path: 'teacherNew', element: <NewTeacher /> },
        { path: 'teacherUpdate/:id', element: <UpdateTeacher /> },
        { path: 'studentUpdate/:id', element: <UpdateStudent /> },
        { path: 'studentNew', element: <NewStudent /> },
        { path: 'products', element: <ProductsPage /> },
        { path: 'blog', element: <BlogPage /> },
      ],
    },
    {
      element: <SimpleLayout />,
      children: [
        { path: '404', element: <Page404 /> },
        { path: '*', element: <Navigate to="/404" replace /> },
      ],
    },
    {
      path: '*',
      element: <Navigate to="/404" replace />,
    },
  ]);

  return routes;
}
