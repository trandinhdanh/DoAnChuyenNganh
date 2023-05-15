import React, { useEffect, useState } from 'react';
import CourseList from '../../components/course-list';
import courseAPI from '../../services/courseAPI';

export default function HomePage() {
  const [data,setData] = useState([])

  useEffect(()=>{
      (async ()=>{
          const response = await courseAPI.getAll()
          setData(response);
      })()
  },[])

  return (
    <div style={{paddingTop:'100px'}}>
      <CourseList data={data}/>
    </div>
  )
}
