
-- US 01
select count(id) from users; --
-- 1855

select count(distinct id) from users;
-- 1855


-- RESULT --> MANUALLY IT IS PASSED


-- US 02
select * from users;



-- US 03
select count(*) from book_borrow
where is_returned=0;

#US5
select bc.name,count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner  join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

select id,name,author from books
where name = 'Meldaca' and author='M Tomruk Usta'
order by id desc;