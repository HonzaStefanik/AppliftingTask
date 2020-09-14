## Running

The app can be run via the included [docker-compose.yml](docker/docker-compose.yml) file with the `docker-compose up -d` command from the `applifting-task/docker` directory.

Required Docker image is neccessary to run it. It can be created by executing the `docker build -f docker/Dockerfile --tag applifting-task .
` command in the project root directory. 

## Configuration

Properties defined in the [application.yml](src/main/resources/application.yml) can be overriden by using the JAVA_OPTS env in the docker compose file definition.

For example, setting a password for the DB can by defining JAVA_OPTS as `JAVA_OPTS="D-spring.datasource.password=pw"`

## Usage

There are several endpoints for CRUD and monitoring operations. Each endpoint requires `x-auth-token` header to be present in order to return a valid response.

#### Endpoint CRUD operations

    GET /endpoint/{userId} 
 
Returns a list of endpoints for the given user



    POST /endpoint/{userId}
    
    body: {
        name: String,
        url: String
    }
    
Adds a new endpoint to the given user

    DELETE /endpoint/{endpointId}
    
Removes the monitored endpoint
    

#### Monitoring operations

    POST /monitoring/{endpointId}
    
Updates the given monitored endpoint.

It is also possible to update all endpoints globally. 
To allow this, property `jobs.endpoint-update.enabled` needs to be set to true.
After that, all endpoints will be updated periodically according to the `jobs.endpoint-update.cron` cron expression.

#### Monitoring results


    GET /result/{endpointId}
    
Returns a list of monitoring results for the given endpoint


## Note

One thing which does not work is authorization. 
As I have never worked with authentication and authorization before, I decided to leave it as the last thing to do.
I couldn't figure out a good way to do authorization.
One way I thought of would be to add a userId to each endpoint on top to retrieve the user and then check if the endpoint is contained within the user's list of endpoints.
I decided not to implement this, as I thought it was a really bad way to do it and it would require additional refactoring.
