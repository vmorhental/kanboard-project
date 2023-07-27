# kanboard-project
- To run tests on Chrome headless please use 
  #### mvn clean test -Dbrowser=chrome -Dheadless=true
- To run tests on Firefox not headless please use 
  #### mvn clean test -Dbrowser=firefox -Dheadless=false
- To run tests on Chrome not headless please use 
  #### mvn clean test -Dbrowser=chrome -Dheadless=false
- To run only API tests please use
  #### mvn clean test -PAPI_tests_run
- To run only UI tests please use
  #### mvn clean test -PUI_tests_run
- To run all tests please use
  #### mvn clean test -PFull_run
- To run for kanban1 cloud please use in all commands
  ####  -DinstanceName="kanban1"
- Localhost env is used as default one to use, all properties are in env.properties file in recources folder
