两种实现方式都是简单实现, 只是说明了原理

第一种轮询的方式:
   原理:
      1 浏览器发送登录请求,获取二维码,将二维码代表的uuid放入application中;
	  2 手机扫码, 获取uuid, 把user放入application中;
	  3 浏览器轮询, 判断user是否为null, 如果不是则登录成功, 同时删除uuid;
	  
   涉及到的多线程操作:
      1 所有登录共享了一个存放键值对的map, 就是这个地方需要同步;
	  2 user的状态不会被改变;
	  3 多个线程发起登录操作, 每个线程获得不同的uuid;
	  4 多个线程扫码, 对于同一个uuid只能一个线程更改状态;
	  5 多个线程轮询, 同一个浏览器一个时间段只有一个线程轮询;
	  
	  
	考虑到安全性: 
	  1 多个手机扫同一个码哪个有效?
	       以轮询时获取的为准
	  2 多个浏览器轮询, 哪一个能登录?
	       可能都成功, 也可能成功一个
		   
		   
    优势: 不占用连接池资源!
	
	劣势: 频繁登录增加服务器负担!
	
	
第二种登录等待的方式:

    原理: 1 浏览器发送登录请求,获取二维码,使用UUID生成一个表示登录动作的entity, 放置与appliaction中
	      2 浏览器发出登录请求, 获取entity, 然后进入等待状态, 等待手机扫码
		  3 手机扫码, 确认浏览器登录请求已经发送, 获取entity, 唤醒等待的线程完成登录


    涉及到的多线程操作: 
       1  所有登录共享了一个存放键值对的map, 就是这个地方需要同步;
	   2  多个线程发起登录操作, 每个线程获得不同的entity, entity设置登录线程, 然后开始等待;       3  多个线程扫码, 每个线程获得不同的entity, entity设置user, 然后唤醒;
	   
	   
	   
	   
	考虑到的线程安全问题:
	   1 发送登录请求, entity是变化的, 并且同时被多个线程访问, 会有多个线层同时等待, 在唤醒时只有最后一个会唤醒,其余超时; 所以需要确保一个uuid接受一个请求; 避免
	   2 扫码时可能有多个手机扫码, entity 设置user和notify的动作被调用多次, 导致不能确认最后登录的是哪个; 可以接受
	   
	   
	优势: 不频繁访系统, 不占用系统资源
	
	   
	劣势: 占用线程资源
       
		   
	  
	  

	  
	  
	  