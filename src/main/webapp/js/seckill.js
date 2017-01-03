/**
 * javascript模块化
 */
var seckill = {
	//封装，秒杀相关额ajax url
	URL : {
		now : function(){
			//return 'http://localhost:8080/moana/tickets/currenttime';
			return 'http://172.24.53.223:8080/moana/tickets/currenttime';
		},
		exposer : function(movieId){
			//return 'http://localhost:8080/moana/tickets/returnuri/'+movieId;
			return 'http://172.24.53.223:8080/moana/tickets/returnuri/'+movieId;
		},
		execution : function(movieId, uri){
			//return 'http://localhost:8080/moana/tickets/rush/'+movieId+"/"+uri;
			return 'http://172.24.53.223:8080/moana/tickets/rush/'+movieId+"/"+uri;
		}
	},
	//处理秒杀逻辑
	handleSeckill : function(movieId, node){
		node.hide().html("<button class='btn btn-primary btn-lg' id='killBtn' >开始抢购</button>");
		//发送post请求拿到秒杀地址
		$.post(seckill.URL.exposer(movieId), {}, function(result){
			//回调方法
			if(result && result['flag']){
				var exposer = result['resultInfo'];
				if(exposer['rush']){
					//开启秒杀
					//获取秒杀地址
					var killUrl = seckill.URL.execution(movieId, exposer['uri']);
					console.log("killUrl:"+killUrl);
					//绑定一次点击事件
					$("#killBtn").one('click',function(){
						//执行秒杀请求操作
						$(this).addClass('disabled');//禁用按钮
						//发送请求
						$.post(killUrl,{},function(result){
							console.log("rushresult :" + result['flag']);
							if(result && result['flag']){
								var killResult = result['resultInfo'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								//显示秒杀结果
								if(state == 1  )
									node.html("<span class='label label-success'>"+stateInfo+"</span>")
								else
									node.html("<span class='label label-danger'>"+stateInfo+"</span>")
							}
						});
					});
					node.show();
				}else{
					//未开启秒杀
					var nowTime = exposer['now'];
					var startTime = exposer['start'];
					var endTime = exposer['end'];
					seckill.countdown(movieId, nowTime, startTime, endTime);
				}
			}else{
				consolg.log('result:'+result);
			}
		})
	},
	//验证邮箱
	validateMail : function(mail){
		if(mail){
			return true;
		}else
			return false;
	},
	//倒计时
	countdown:function(movieId, nowTime, startTime, endTime){
		var seckillBox = $("#seckill-box");
		//时间判断
		if(nowTime > endTime ){
			//抢购结束
			seckillBox.html('抢购结束');
		}else if(nowTime < startTime){
			//抢购未开始
			var killTime = new Date(startTime+1000);
			seckillBox.countdown(killTime, function(event){
				var format = event.strftime('抢购倒计时：%D天 %H时 %M分 %S秒');
				seckillBox.html(format);
			}).on("finish.countdown",function(){
				//获取抢购地址，控制显示逻辑
				seckill.handleSeckill(movieId, seckillBox);
			});
		}else{
			//秒杀开始
			seckill.handleSeckill(movieId, seckillBox);
		}
	},
	//秒杀逻辑
	detail:{
		//详情页初始化
		init : function(params){
			//手机验证和登录,计时交互
			var userMail = $.cookie('usermail');
			if(!seckill.validateMail(userMail)){
				//绑定mail
				var killMailModal = $("#killMailModal");
				killMailModal.modal({
					show:true,//显示模态框
					backdrop:'static',//禁止位置关闭
					keyboard:false
				});
				$("#killMailBtn").click(function(){
					var mail = $("#killMailKey").val();
					if(seckill.validateMail(mail)){
						$.cookie("usermail", mail, {expires:7,path:'moana'});
						window.location.reload();
					}else{
						$("#killMailMessage").hide().html("<label class='label label-danger'>邮箱格式错误</label>").show(300);
					}
				});
			}
			//已经登录
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var movieId = params['movieId'];
			
			$.get(seckill.URL.now(), {}, function(result){
				console.log("result: "+result['flag']);
				if(result && result['flag']){
					var nowTime = result['resultInfo'];
					//时间判断
					seckill.countdown(movieId, nowTime, startTime, endTime);
					
				}else{
					console.log("result: "+result['resultInfo']);
				}
			})
		}
	}
};

