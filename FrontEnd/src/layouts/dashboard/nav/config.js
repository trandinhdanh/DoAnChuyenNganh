// component
import SvgColor from '../../../components/svg-color';

// ----------------------------------------------------------------------


const icon = (name) => <SvgColor src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />;



const navConfigAdmin = [
  {
    title: 'dashboard',
    path: '/dashboard/app',
    icon: icon('ic_analytics'),
  },
  {
    title: 'Teacher',
    path: '/dashboard/teacher',
    icon: icon('ic_user'),
  },
  
];

const navConfigTeacher = [

{
  title: 'Student',
  path: '/dashboard/student',
  icon: icon('ic_user'),
},

 ];


const navConfigStudent = [
{
  title: 'product',
  path: '/dashboard/products',
  icon: icon('ic_cart'),
},
{
  title: 'blog',
  path: '/dashboard/blog',
  icon: icon('ic_blog'),
},
];
export {navConfigAdmin,navConfigTeacher,navConfigStudent};

