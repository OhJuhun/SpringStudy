
# Settings

<details markdown="1">
<summary>접기/펼치기</summary>

## Spring Web Starter
    RESTful API, Spring MVC 등에 필요
## Thymeleaf
    Serverside java template Engin
    Spring boot 등에서 JSP를 최근에 사용하지 않음
        장점
            Natural template (MarkUp을 깨지 않고 사용-> 웹브라우저에서 열림)
            resources:templates/ +{ViewName}+.html 자동 매핑
## Spring Data JPA
    Pesistent JPA
    Java Object와 DB를 연동
## Hikari CP
    Database Connection Pool
## H2 Database
    간단하게 사용가능한 Database 테스트 용도 
    Memory mode로 사용 가능
## Lombok
    getter setter 등을 Auto Generation.
    But, Kotlin과의 호환성에 문제가 있음
    사용하려면 Enable Annotation Processors 설정 필요.
## devtools
    짱짱 라이브러리 짱 편함 자동 restart 등 지원
## persistent.xml & LocalContainerEntityManagerFactoryBean
    이러한 복잡한 설정들이 다 자동화되어 있음
    추가설정은 매뉴얼
## spring-boot-data-source-decorator
    query Log (?,?) - > (a,b)
    이러한 라이브러리 등은 배포시 성능 관련하여 고민을 해보아야 함.

</details>

# Keywords

<details markdown="1">
<summary>접기/펼치기</summary>

## Optional
    값을 발견하지 못한 경우 null return
    미사용시 [], '' return
    Lambda로 주로 사용
    return OptionalObj.map(response ->
            new ResponseEntity(response,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));

## ResponseEntity
    HttpEntity를 상속 -> HttpHeader, Body를 가질 수 있다.
    HttpStatus와 함께 사용 -> 협업시 최적화된 모듈
    ResponseEntity.ok(Object)
    ResponseEntity(msg,httpStatus) -> Best?
    ResponseEntity(httpStatus)

## EntityManager vs JpaRepository
    EntityManager 인터페이스
    JpaRepository (Spring Data JPA의 핵심) 위와 같은 인터페이스의 구현체 

![JPAvsSpringDataJPA](https://github.com/OhJuhun/SpringStudy/blob/master/mdresources/JPAvsSpringDataJPA.png)

## Memory DB
    Spring Boot 사용시 쉽게 사용가능

## Controller vs RestController
    Controller      주로 view 반환 시 사용. Data 반환시 ResponseBody Annotation 추가
    RestController  Controller에 ResponseBody가 추가된 것

## Domain Model Pattern
    Entity에 핵심 Business Logic을 몰아 넣어
    Service에서 위임만 하여 사용할 수 있게 한다.
    ORM 사용시 이 패턴을 많이 사용함
    
## Transaction Script Pattern
    Entity에는 Business Logic이 거의 없고
    Service에 몰아 넣어 처리한다.

## Validation
    FE -> Server side와 통신하지 않아도 되므로 속도가 빠름(보안 취약)
    BE -> 복잡한 화면 구성에 있어서는 어려운 점이 존재
    --> 둘을 적절하게 섞어서 활용(Trade Off)하는 것이 속도, 보안성 측면에서 유리

## UserForm과 UserEntity를 나누어 개발하는 이유
    두 곳에서 원하는 Validiation이 서로 다를 수 있음
    Entity에서 다른 Entity와 관계를 맺고 있다면 삽입되는 Field와 Entity Field가 차이날 수 있다.
    Entity는 다른 곳에 Dependency가 없이, 핵심 Business Logic에만 Dependency를 갖게 설계
    --> DTO(Getter Setter만 있는 Object == UserForm)
    특히 API 설계시에는 ***** 절대 Entity를 넘기면 안된다 *****
    API Spec이 변할 수 있고, Password 등이 노출될 수 있다.

## API parameter
    ResponseBody에 Map<String,Object> 보다 Entity를 받는 것이 유지 보수에 좋음
    
## JPA에서의 수정
### 준영속 Entity
    JPA 영속성 컨텍스트가 더이상 관리하지 않는 Entity
    Entity가 지금 생성되었지만, 이에 set되는 값이 JPA가 가져온 것인 경우
    즉, 임의로 만들어 냈어도 기존의 식별자를 가지고 있는 Entity
    ***** 이는 JPA가 관리하지 않음 *****
    -> 변경감지 또는 병합으로 해결
### 변경 감지 (= Ditry Checking)
    Transaction 중에 가져온 Entity 내 값이 변경되면 JPA가 알아서 Persist
    Modify시 Id값과 Entity를 인자로 받아와서 Id로 persistent entity를 검색 후, 변경 값을 대입하면 save가 없어도 변경됨
    
### 병합
    EntityManager의 Merge.
    변경 감지에서 필요한 로직을 알아서 해결해줌(실무에선 사용 X)
    ? 병합 시 값이 없으면 NULL로 교체해 버리기 때문!!!
    JpaRepository의 save
    
## Dynamic Query in JPA
### JPA Criteria
    JPA Standard But, 유지보수성이 매우 낮아, 실무에서 사용하지 않음
    무슨 Query인지 코드를 보고 떠올리기 힘듬 
    --> QueryDSL

### *** QueryDSL ***
    Compile 시점에 오타 파악 가능
    직관적인 Method로 Query 파악 가능
    복잡한 Query / Dynamic Query 이해 쉬움
    실무에서 유용하게 많이 사용

### JpaSpecificationExecutor
    findAll(Specification<T> spec) 등으로 사용하여 where절 대체

### QueryDslPredicateExecutor
    Spring Data JPA 제공 페이징, 정렬 기능도 함께 사용 가능

### QueryDslRepositorySupport
    QueryDSL의 모든 기능을 사용하기 위해 JPAQuery 객체를 직접 생성하여 사용

### Custom Repository
    -interface ARepositoryCustom
    -interface ARepositoryImpl extends JpaRepository<A,Long>, ARepositoryCustom

</details>

 
# Error & Warn

</details>

<details markdown="1">
<summary>접기/펼치기</summary>

## Error
### SQLGrammarException: could not extract ResultSet
    Entity : Table 매핑이 잘못 되었을 때 발생
### DuplicateMappingException

### IlligalStateException
    중복된 값 insert시 발생시키는 Exception

### InvalidDefinitionException
    hibernate Lazy loading Exception
    연관관계가 있는 Entity를 Lazy하게 가져오려다가 실패
    해결
        Object == JSON 이어야 할 경우 FetchType=LAZY를 없애준다
        else @JsonIgnore을t nested 객체에 붙인다.
## Warn
### uses unchecked or unsafe operations
    경고 제거를 위해 raw Type -> Type 지정
    Ex) ResponseEntity -> ResponseEntity<Object>
    
</details>

# Annotations

<details markdown="1">
<summary>접기/펼치기</summary>

## **** @Autowired ****
    타입과 맞는 것을 찾아 자동 연결
## @Transactional
    트랜잭션화로 자동 RollBack이 가능하다.
    주로 modify, delete 등의 Query에 사용
    Entity Manager에 의한 데이터 변경은 항상 Transaction 안에서 이루어 져야 함
    같은 영속성 Container에서 같은 Entity를 참조하면 같은 값
    readOnly로 성능 최적화 가능
## @RunWith(class)
    Spring Boot Test라는 것을 알림  
## @Embedded, @Embeddable
    Value Type(Immutable 해야 함) 객체 사용시 적용 
    Non-Arugment Constructor r-> protected
    All-Argument Constructor -> public으로 선언

## JoinColumn
    객체간 관계 설정 후 매핑할 때 사용
    OneToMany 관계에서 필수적으로 설정되어야 함

### insertable, updatable

## Qualifier
    Paging시 사용해야 할 정보가 둘 이상일 경우 접두사를 붙여 정의

## PageableDefault
    Pageable 기본값(page=0,size=20)을 변경하고 싶을 경우 사용

## Reltation
### ***** 모든 연관관계는 fetch = FetchType.LAZY *****
    EAGER(즉시로딩)은 예측이 어렵고 어떤 SQL이 실행될 지 추적이 어렵다!! 특히 JPQL에서 N+1 문제 발생
    하나를 가져오면 모든 연관 관계를 Join하여 다 긁어옴 
    LAZY(지연로딩)에서 발생하는 문제는 Fetch Join으로 해결 가능!!
### @OneToOne
    Default : EAGER
    CascadeType : Persist (저장)을 Mapping Entity에 전파한다.
### @OneToMany
    Default : LAZY
    1:N 관계
    User -< Rental
    양방향일 경우 MappedBy로 매핑
### @ManyToOne
    Default : EAGER
    N:1 관계
    Rental -< User
### @ManyToMany
    Default : LAZY

</details>
