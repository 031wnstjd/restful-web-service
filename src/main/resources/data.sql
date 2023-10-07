insert into member values(90001, now(), 'User1', 'test1111', '701010-1111111')
insert into member values(90002, now(), 'User2', 'test2222', '801010-2222222')
insert into member values(90003, now(), 'User3', 'test3333', '901010-1111111')

insert into post(post_id, description, member_id) values(10001, 'My first post', 90001)
insert into post(post_id, description, member_id) values(10002, 'My second post', 90001)