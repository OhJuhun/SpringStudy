
# Settings

<details markdown="1">
<summary>접기/펼치기</summary>

## Spring Web Starter
    RESTful API, Spring MVC 등에 필요
## Thymeleaf
    Serverside java template Engine
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
# Keywords
## Optional
    값을 발견하지 못한 경우 null return
    미사용시 [], '' return

## ResponseEntity
    HttpEntity를 상속 -> HttpHeader, Body를 가질 수 있다.
    HttpStatus와 함께 사용 -> 협업시 최적화된 모듈
    ResponseEntity.ok(Object)
    ResponseEntity(msg,httpHeaders,httpStatus)  -> Best?
    ResponseEntity(httpStatus)
## EntityManager vs JpaRepository
    EntityManager 인터페이스
    JpaRepository (Spring Data JPA의 핵심) 위와 같은 인터페이스의 구현체 

![JPAvsSpringDataJPA](https://github.com/OhJuhun/SpringStudy/blob/master/mdresources/JPAvsSpringDataJPA.png)

## Controller vs RestController
    Controller      주로 view 반환 시 사용. Data 반환시 ResponseBody Annotation 추가
    RestController  Controller에 ResponseBody가 추가된 것
</details>


# Error & Warn

</details>

<details markdown="1">
<summary>접기/펼치기</summary>

## Error
### SQLGrammarException: could not extract ResultSet
    Entity : Table 매핑이 잘못 되었을 때 발생
### DuplicateMappingException

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
## @RunWith(class)
    Spring Boot Test라는 것을 알림  
## @Embedded, @Embeddable
    Value Type(Immutable 해야 함) 객체 사용시 적용 
    Non-Arugment Contstructor -> protected
    Agument Constructor -> public으로 선언

## JoinColumn
    객체간 관계 설정 후 매핑할 때 사용
    OneToMany 관계에서 필수적으로 설정되어야 함

### insertable, updatable

## @OneToOne

## @OneToMany
    1:N 관계
    User -< Rental
    양방향일 경우 MappedBy로 매핑
## @ManyToOne
    N:1 관계
    Rental -< User
## @ManyToMany
    
</details>
