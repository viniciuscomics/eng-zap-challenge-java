### Code Challenge Grupo ZAP

## Introdução

Com a fusão do Grupo ZAP e Viva Real, surgiu a necessidade da evolução da api atual.
Onde será necessário aplicar um filtro em uma lista de imoveis para obter os imoveis elegiveis para o grupo Zap e Viva Real.

## Informações do projeto

	1. O projeto foi desenvolvido com Java 8 e utilizando o framework Spring Boot v2.2.2.
	2. Para testes unitários foi utilizadoo Junit 5
	
## Pré Requisitos 
	1. Ter o Java 8 instalado
	2. Ter o Maven Instalado
	
## Compilando o projeto

	1. Apos ter baixado o projeto, abra um terminal de comando(ex: git bash)  no diretório do projeto.
	2. Digite o comando "mvn clean package" e aguarde o fim da compilação.
		
## Configurações da API - Propiedades da aplicacao

    1. Application.properties
	
    spring.profiles.active=dev  
	 
    grupozap.url-busca=http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/source-2.json
    grupozap.origin-allow=http://localhost:8080
    grupozap.minlon =-46.693419
    grupozap.minlat =-23.568704
    grupozap.maxlon =-46.641146
    grupozap.maxlat =-23.546686
    
    zap.param-min-rental-total-price=3500
    zap.param-min-sale-price=600000
    # - 10%
    zap.bounding-box-fee=0.9 
    
    viva.param-max-rental-total-price=4000
    viva.param-max-sale-price=700000
    # + 50%
    viva.bounding-box-fee=1.5
	
	2. Temos os parametros configuraveis para facilitar a troca de ambientes e parametros referentes a regra de negocio.
	
	#OBS
	Temos o parametro "grupozap.origin-allow" para que possa ser passado a url de origem permitidae e a requisição não trave no cors.
	
## Executando os testes unitários
		1. Apos ter baixado o projeto, abra um terminal de comando(ex: git bash)  no diretório do projeto.
		2. Digite o comando "mvn test" e aguarde o fim dos testes.
		 
## Executando a API no ambiente local

	1. Após ter compilado o projeto, vá até a pasta target que se encontra no diretório do projeto.

	2. Na pasta target, copie o .jar da API para um local de sua escolha e abra o terminal de comando(ex: git bash)
	
	3. Digite o comando "java -jar o_jar_da_sua_api.jar"
	
	4. Aguarde o sua api inicializar, se tudo der certo as últimas mensagens impressas no console será;
		
		Tomcat initialized with port(s): xxxx (http)
		Starting service [Tomcat]
		Starting Servlet engine: [Apache Tomcat/9.0.29]
		Initializing Spring embedded WebApplicationContext
		Root WebApplicationContext: initialization completed in 2664 ms
	
