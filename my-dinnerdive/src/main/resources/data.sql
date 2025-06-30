INSERT INTO restaurants 
(restaurant_id, restaurant_name, category, image_url, visited_count, last_eat, last_visited_at, note) VALUES
(1,'兩藍拉麵','主食','https://images.unsplash.com/photo-1614563637806-1d0e645e0940?q=80&w=1973&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',3,'2025-06-06 17:37:50','2025-05-18 23:06:13','拉麵'),
(2,'MoMo參番屋','主食','https://images.unsplash.com/photo-1694953593181-6ce423500712?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',1,'2025-05-24 12:00:00','2025-05-18 23:06:13','咖哩'),
(3,'鬆餅老師','輕食','https://images.unsplash.com/photo-1669277038512-2dc8b3a2aac8?q=80&w=2048&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',1,'2025-05-24 15:06:58','2025-05-18 23:06:13','鬆餅，可以當飯吃'),
(4,'必吃客','主食','https://images.unsplash.com/photo-1590947132387-155cc02f3212?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',1,'2025-05-24 12:00:00','2025-05-18 23:06:13','披薩'),
(5,'大口屋','主食','https://images.unsplash.com/photo-1700323449261-5332ce054718?q=80&w=1931&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-05-24 15:14:52','丼飯'),
(6,'陪里尼','主食','https://images.unsplash.com/photo-1673442635965-34f1b36d8944?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-05-24 15:30:31','義大利麵'),
(7,'尊爵世家','主食','https://images.unsplash.com/photo-1565299715199-866c917206bb?q=80&w=1980&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-05-24 16:53:58','牛排'),
(8,'柒柒陸牛肉麵','主食','https://images.unsplash.com/photo-1631709497146-a239ef373cf1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-06-08 23:49:21','牛肉麵'),
(9,'NoNo都不','飲料','https://images.unsplash.com/photo-1461023058943-07fcbe16d735?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-06-14 14:51:48','奶茶加點布丁好喝'),
(10,'六十籃','飲料','https://images.unsplash.com/photo-1525803377221-4f6ccdaa5133?q=80&w=2074&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-06-14 15:06:40','奶茶還不錯喝'),
(11,'斜堡弟','輕食','https://images.unsplash.com/photo-1610970878459-a0e464d7592b?q=80&w=2124&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-06-14 15:03:47','漢堡店'),
(12,'謬可','飲料','https://images.unsplash.com/photo-1563636619-e9143da7973b?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',0,NULL,'2025-06-14 15:05:47','招牌是牛奶');

INSERT INTO users 
(user_id, account, member_password, roles, created_date, last_modified_date) VALUES
(1,'SS','202cb962ac59075b964b07152d234b70','管理員','2025-06-29 17:12:41','2025-06-30 11:28:27'),
(2,'123','202cb962ac59075b964b07152d234b70','一般帳號','2025-06-29 17:12:41','2025-06-30 11:28:27');