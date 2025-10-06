SELECT G.SCORE, E.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL  
FROM (SELECT SUM(SCORE) AS SCORE, EMP_NO
      FROM HR_GRADE
      GROUP BY EMP_NO
     ) AS G
JOIN HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO
WHERE G.SCORE =  (SELECT MAX(SCORE)
                  FROM (SELECT SUM(SCORE) AS SCORE, EMP_NO
                        FROM HR_GRADE
                        GROUP BY EMP_NO
                       ) AS A
                 )