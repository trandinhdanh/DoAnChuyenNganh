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
import ExamPage from './pages/exam/ExamPage';
import CoursePage from './pages/course/CoursePage';
import UpdateCourse from './pages/course/UpdateCourse';
import UpdateExam from './pages/exam/UpdateExam';
import QuestionPage from './pages/question/QuestionPage';
import NewQuestionPage from './pages/question/NewQuestionPage';
import UpdateQuestionPage from './pages/question/UpdateQuestionPage';
import AddStudentToCourse from './pages/course/AddStudentToCourse';
import StudentOfCoursePage from './pages/course/StudentOfCoursePage';
import GuestCoursePage from './pages/guest/GuestCoursePage';
import GuestExamPage from './pages/guest/GuestExamPage';
import GuestQuestionPage from './pages/guest/GuestQuestionPage';



// // ----------------------------------------------------------------------





export default function Router() {

  const adminDefaultPath = "/dashboard/app";
  const teacherDefaultPath = "/dashboard/student";
  const studentDefaultPath = "/dashboard/courseStudent";
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
        { path: "exam/:id", element: <ExamPage /> },
        { path: "examUpdate/:id", element: <UpdateExam /> },
        { path: "course", element: <CoursePage /> },
        { path: "courseUpdate/:id", element: <UpdateCourse /> },
        { path: "question/:id", element: <QuestionPage /> },
        { path: "questionNew/:id", element: <NewQuestionPage /> },
        { path: "questionUpdate/:id", element: <UpdateQuestionPage /> },
        { path: "course/:id/add", element: <AddStudentToCourse /> },
        { path: "course/:id/students", element: <StudentOfCoursePage /> },
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
        { path: "courseStudent", element: <GuestCoursePage /> },
        { path: "courseStudent/:id", element: <GuestExamPage /> },
        { path: "questionStudent/:id", element: <GuestQuestionPage /> },
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