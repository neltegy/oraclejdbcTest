/*
1) EMPLOYEE 테이블에서 이름(Last Name)에 “hae”를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID 를 출력하라. 
*/
select employee_id
      ,first_name
      ,last_name
      ,e.department_id
from
    (select department_id
    from employees
    where last_name like '%hae%')s
    ,employees e
where s.department_id = e.department_id
order by employee_id desc;


/*
2) 각 도시(city)별 가장 연봉을 많이 받고 있는 사원의 도시 이름(City), First Name, Last Name, 급여를 조회하라. 
급여 순으로 오름차순 정렬하시오. (1-2.sql)
*/  
select s.city
      ,first_name
      ,last_name
      ,s.salary
from
    (select city
           ,max(salary) salary
    from employees e
        ,departments d
        ,locations l
    where e.department_id = d.department_id
      and d.location_id = l.location_id
    group by city)s
    ,(select city
            ,first_name
            ,last_name
            ,salary
        from employees e
            ,departments d
            ,locations l
        where e.department_id = d.department_id
          and d.location_id = l.location_id
       )t
where s.salary = t.salary
  and s.city = t.city
order by s.salary;



