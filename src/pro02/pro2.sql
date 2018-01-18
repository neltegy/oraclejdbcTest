/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */
select country_name
      ,s.salary
from
    (select max(salary) salary
    from
        (select round(avg(salary),2) salary
        from employees e
            ,departments d
            ,locations l
            ,countries c
        where e.department_id = d.department_id
          and d.location_id = l.location_id
          and l.country_id = c.country_id
        group by country_name)
    )t
    ,(select country_name
              ,round(avg(salary),2) salary
        from employees e
            ,departments d
            ,locations l
            ,countries c
        where e.department_id = d.department_id
          and d.location_id = l.location_id
          and l.country_id = c.country_id
        group by country_name
    )s
where t.salary = s.salary;

/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/
select region_name
      ,s.salary
from
    (select max(salary) salary
    from
        (select round(avg(salary),2) salary
        from employees e
            ,departments d
            ,locations l
            ,countries c
            ,regions r
        where e.department_id = d.department_id
          and d.location_id = l.location_id
          and l.country_id = c.country_id
          and c.region_id = r.region_id
        group by region_name)
    )t
    ,(select region_name
              ,round(avg(salary),2) salary
        from employees e
            ,departments d
            ,locations l
            ,countries c
            ,regions r
        where e.department_id = d.department_id
          and d.location_id = l.location_id
          and l.country_id = c.country_id
          and c.region_id = r.region_id
        group by region_name
    )s
where t.salary = s.salary;


/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/
select department_name
      ,t.emnum
from
    (select max(emnum) emnum
    from
        (select count(employee_id) emnum
        from employees
        group by department_id)
    )s
    ,(select department_id
            ,count(employee_id) emnum
        from employees
        group by department_id
    )t
    ,departments d
where s.emnum = t.emnum
  and t.department_id = d.department_id;


