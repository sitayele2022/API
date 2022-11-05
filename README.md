# API Automation Test framework
API Automation Test is RestAssured based TestNG framework to perform API testing.

### Pre-requisite
![Java](https://img.shields.io/badge/-Java%20JDK-%23007396?logo=java&logoColor=black&)
![Maven](https://img.shields.io/badge/-Maven-C71A36.svg?logo=Apache&logoColor=white)
![NodeJs](https://img.shields.io/badge/-NodeJS-%23339933?logo=npm&logoColor=white)

### Getting Started
```
1. git clone https://github.com/sadabnepal/APIRestAssuredTestNGFramework.git
2. Navigate to APIRestAssuredTestNGFramework
```

### API Sources:
    - Localhost: http://localhost:3000/
    - ReqRes API: https://reqres.in/

### Running Test
- Navigate to `src/main/app` folder and run below commands
    - `npm install` to install required dependencies for local api (nodejs required)
    - `npm start` to start local api server (leave the terminal opened) 
- Open other terminal from root project folder and run `mvn test`  OR
- Open project in code editor like eclipse or intellij idea ==> right click on testng.xml ==> run as testng.xml

### Report Path
```
<ROOT_PROJECT>/reports/index.html
```

### Sample Report
![image](https://user-images.githubusercontent.com/65847528/145385784-766415b6-9591-4055-ac18-6d44cd4bbe5e.png)
