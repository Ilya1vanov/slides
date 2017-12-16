# TEST PLAN OUTLINE (IEEE 829 FORMAT)

1. Test Plan Identifier
2. References
3. Introduction
4. Test items
5. Software Risk Issues
6. Features to be tested
7. Features not to be tested
8. Approach
9. Item Pass/Fail Criteria
10. Suspension Criteria and Resumption Requirements
11. Test Deliverables
12. Enviromental Needs
13. Staffing and Training Needs
14. Responsibilities

## 1. Test plan identifier
Current test plan identifier (date and time format - ddmmyyyyhhmm): 101220172228

## 2. References
- [SRC.md](../requirements.md)
- [Use-cases.jpg](../use-case/use-case.jpg)
- [Deployment.jpg](../deployment.jpg)
- [Glossary.md](../glossary.md)

## 3. Introduction


## 4. Test items
- frontend
- backend

## 5. Software Risk Issues
- Heroku deployment stability issues
- Usage of unstable packages and migrations
- Discrepancy of the original requirements

## 6. Features to be tested
1. Browsing content of web app
2. Server API rest API

## 7. Features not to be tested
1. Adding pictures to presentations

## 8. Approach (Strategy)
- Used tools: Protractor, JUnit, Spring Test, Karma, Jasmine
- Functional tests for client side app check the following features:
 1. Browsing content of web app
- Integration and unit tests for backend check rest API. Testing controllers and services.

## 9. Item pass/fail criteria
- All Unit tests must be passed
- Code coverage must be at least 30%

## 10. Suspension criteria and resumption requirements
- Test suspension criteria - 12 hours of unsuccessfull attempts to test case.

## 11. Test Deliverables

Test results must be desribed by the following plan:

1. Id (date and time format - ddmmyyyyhhmm): 101220172228
2. Name
3. Scenario
4. Expected result
5. Actual result
6. Mark

### Test cases:

#### 1 Login
1. 141220161106
2. 'Login' REST API endpoint
3. Request endpoint with valid user and application credentials
4. Api should return access_token with status 200
5. 
6. 

#### 2 Get User
1. 141220171106
2. 'Get user' REST API endpoint
3. Request endpoint with authenticated user
4. Api should return user object with status 200
5. 
6. 

#### 3 Get all presentations
1. 161220172147
2. 'Get all presentations' REST API endpoint
3. Request endpoint with authenticated user
4. Api should return all presentations which belongs to user with status 200
5. 
6. 

#### 4 Create presentation
1. 161220172204
2. 'Create presentation' REST API endpoint
3. Request endpoint with authenticated user
4. Api should return new presentation object with status 200
5. 
6. 

#### 5 Delete presentation
1. 161220172204
2. 'Delete presentation' REST API endpoint
3. Request endpoint with authenticated user. Send id of presentation to remove
4. Api should return empty response with status 200
5. 
6. 

#### 6 Get particular presentation(s)
1. 161220172204
2. 'Delete presentation' REST API endpoint
3. Request endpoint with authenticated user. Send id(s) of required presentations
4. Api should return empty response with status 200
5. 
6. 

#### 7 Edit presentation
1. 161220172204
2. 'Delete presentation' REST API endpoint
3. Request endpoint with authenticated user. Send edited presentation object
4. Api should return empty response with status 200
5. 
6. 

#### 8 Edit slide(s)
1. 161220172204
2. 'Edit slide' REST API endpoint
3. Request endpoint with authenticated user. Send edited slide(s) object
4. Api should return empty response with status 200
5. 
6. 

## 12. Enviromental needs
- Separate test database H2

## 13. Staffing and training needs
- Need lection about Protractor, Karma, Jasmine + Webpack framework/tools to test frontend.
- Need knoleage of using JUnit and Spring Test framework to test backend.

## 14. Responsibilities
- Designate risks
- Select features to be tested and not tested
- Set overall strategy for this level of plan
- Ensure all requred elements are present
