# Security

## **`개요`**
    Spring Security의 Content-negotiation 전략인 httpBasic 또는 formLogin에 의존함
    
### default
- UserDetailService는 Single User를 갖는다.(usrename=user,password는 Using generated security password: password)
- 이를 사용하여 Login
- spring.security.user.name, spring.security.user.password로 default값 변경 가능

#### features
- 메모리에 저장하는 UserDetailsService 또는 WebFlux 애플리케이션에서의 ReactiveUserDetailsService bean을와 single user (id,pw) 제공
- 앱 전체에 사용되는 Form-based Login, request header의 Accept을 필요로하는 Http Basic Security (actuator가 경로에 있으면 actuator의 end-point까지 포함)
- 인증(Authentification)을 위한 DefaultAuthenticationEventPublisher 제공
- Bean을 추가해서 `AuthenticationEventPublisher`를 커스터마이징 가능

## **`MVC Security`**

### default
#### SecurityAutoConfiguration 
    web Security를 위한 SpringBootWebSecurityConfiguration import

#### UserDetailsServiceAutoConfiguration
    Non-Web Security를 위한 UserDetailsServiceAutoConfiguration import

#### Switch OFF
- default web application security를 완전히 사용하지 않거나, OAuth 등과 혼용을 위해 WebSecurityConfigurerAdapter타입의 bean 추가
- **UserDetailsService configuration or Actuator’s security는 비활성되지 않음**
- UserDetailsService까지 비활성하기 위해 UserDetailsService, AuthenticationProvider, AuthenticationManager 추가

### Customizing
- WebSecurityConfigurerAdapter 커스터마이징을 위해 Actuator end-point 또는 정적 리소스에 접근을 위한 편리한 Overriding 가능 method 제공
- EndpointRequest는 management.endpoints.web.base-path에 기반한 RequestMatcher 구현에 사용
- PathRequest는 일반적으로 사용되는 Resource Location을 위한 RequestMatcher 구현에 사용

## **`OAuth`**
    Spring이 지원하는 인가(authorization) 프레임워크

### **CLIENT**
- spring-security-oauth2-client로 OAuth2/Open ID Connect clients 설정 Auto Config
- 다수의 OAuth2 client와 provider 등록 가능

### spring.security.oauth2.client properties
```properties
    spring.security.oauth2.client.registration.my-client-1.client-id=abcd
    spring.security.oauth2.client.registration.my-client-1.client-secret=password
    spring.security.oauth2.client.registration.my-client-1.client-name=Client for user scope
    spring.security.oauth2.client.registration.my-client-1.provider=my-oauth-provider
    spring.security.oauth2.client.registration.my-client-1.scope=user
    spring.security.oauth2.client.registration.my-client-1.redirect-uri=https://my-redirect-uri.com
    spring.security.oauth2.client.registration.my-client-1.client-authentication-method=basic
    spring.security.oauth2.client.registration.my-client-1.authorization-grant-type=authorization_code

    spring.security.oauth2.client.registration.my-client-2.client-id=abcd
    spring.security.oauth2.client.registration.my-client-2.client-secret=password
    spring.security.oauth2.client.registration.my-client-2.client-name=Client for email scope
    spring.security.oauth2.client.registration.my-client-2.provider=my-oauth-provider
    spring.security.oauth2.client.registration.my-client-2.scope=email
    spring.security.oauth2.client.registration.my-client-2.redirect-uri=https://my-redirect-uri.com
    spring.security.oauth2.client.registration.my-client-2.client-authentication-method=basic
    spring.security.oauth2.client.registration.my-client-2.authorization-grant-type=authorization_code

    spring.security.oauth2.client.provider.my-oauth-provider.authorization-uri=https://my-auth-server/oauth/authorize
    spring.security.oauth2.client.provider.my-oauth-provider.token-uri=https://my-auth-server/oauth/token
    spring.security.oauth2.client.provider.my-oauth-provider.user-info-uri=https://my-auth-server/userinfo
    spring.security.oauth2.client.provider.my-oauth-provider.user-info-authentication-method=header
    spring.security.oauth2.client.provider.my-oauth-provider.jwk-set-uri=https://my-auth-server/token_keys
    spring.security.oauth2.client.provider.my-oauth-provider.user-name-attribute=name
```
### Open ID Connect(API Login)
- Open ID Connect Discovery를 지원하는 Provider를 위한 보다 심플한 Configuration 제공
- Provider는 Issuer Identifier인 URI(isuue-uri)를 Configure해야 한다.
#### Example
- issuer-uri가 https://example.com로 제공된다면, `OpenID Provider Configuration Request`는 `"https://example.com/.well-known/openid-configuration"`로 만들어져야 한다.
- result : `OpenID Provider Configuration Response`
##### Example properties
- spring.security.oauth2.client.provider.oidc-provider.issuer-uri=`https://dev-123456.oktapreview.com/oauth2/default/`

##### default
- OAuth2LoginAuthenticationFilter는 `/login/oauth2/code/*` 만 접근
- `redirect-uri`를 다른 패턴으로 커스터마이징 하기 위해 Custom Pattern을 제공하는 Configuration을 제공해야 한다.
- Custom WebSecurityConfigurerAdapter를 `Example Java Code`와 같이 제공할 수 있다.

##### `Example Java Code`
```java
    public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .oauth2Login()
                .redirectionEndpoint()
                    .baseUri("/custom-callback");
    }
}
```
### **Resource Server**
- spring-security-oauth2-resource-server로 Set-up
- JWT Configuration을 위해 `JWK Set URI` 나 `OIDC Issuer URI`가 특정되어야 함 


#### Example
```properties
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=`https://example.com/oauth2/default/v1/keys`
```
#### Example
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=`https://dev-123456.oktapreview.com/oauth2/default/`
```
#### INFO
- Authorization Server가 JWT Set URI를 지원하지 않는다면, JWT 서명에 사용되는 `Public Key`로 `Resource Server` 구성 가능
- ```properties spring.security.oauth2.resourceserver.jwt.public-key-location```proterty를 사용하는 것으로 해결 가능
- 이 값은 `PEM-encoded x509` 포맷의 Public Key를 포함하는 파일로 설정되어야 한다.


### Customizing
- `servlet`과 `reactive applications`에 동일 properties 적용 가능 (default)
- `JwtDecoder` bean으로 `servlet application`
- `ReactiveJwtDecoder` bean으로 `reactive application`

### Opaque Tokens
- JWT 대신 불투명한 토큰이 사용되었을 경우, `token properties`처럼 해결할 수 있다.
- `servlet`, `reactive` 모두에 적용이 가능하다.
- `OpaqueTokenIntrospector`로 servlet, `ReactiveOpaqueTokenIntrospector` reactive customizing 가능

### `token proerties`
```properties
spring.security.oauth2.resourceserver.opaquetoken.introspection-uri=https://example.com/check-token
spring.security.oauth2.resourceserver.opaquetoken.client-id=my-client-id
spring.security.oauth2.resourceserver.opaquetoken.client-secret=my-client-secret
```

### **Authorization Server**
    NOT SUPPORT
