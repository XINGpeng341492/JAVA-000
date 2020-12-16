package com.xp.kid.rpcfx.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xp.kid.rpcfx.api.RpcfxRequest;
import com.xp.kid.rpcfx.api.RpcfxResolver;
import com.xp.kid.rpcfx.api.RpcfxResponse;
import com.xp.kid.rpcfx.demo.api.OrderService;
import com.xp.kid.rpcfx.demo.api.UserService;
import com.xp.kid.rpcfx.server.RpcfxInvoker;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Autowired
	RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		return invoker.invoke(request);
	}

	@Bean
	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
		return new RpcfxInvoker(resolver);
	}

	@Bean
	public RpcfxResolver createResolver(){
		return new DemoResolver();
	}

	// 能否去掉name
	//
	@Bean(name = "com.xp.kid.rpcfx.demo.api.UserService")
	public UserService createUserService(){
		return new UserServiceImpl();
	}

	@Bean(name = "com.xp.kid.rpcfx.demo.api.OrderService")
	public OrderService createOrderService(){
		return new OrderServiceImpl();
	}

}
