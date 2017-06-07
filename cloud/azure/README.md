# VSTS Deployment

## Instructions

### App Service 

#### Source code
- Move `web.config` in to project root
#### VSTS
- Create new Build Definition from [Gradle template](https://docs.microsoft.com/en-us/azure/app-service-web/web-sites-java-custom-upload)
- Adjust `Copy Files to: $(build.artifactstagingdirectory)` task, set `Contents` input:
   ```
    api/build/libs/api.jar
    web.config
   ```
- Create new `App Service` Release:
  -  Adjust `Package or folder` input to reference `drop` directory
#### Azure Portal
- Set Java version to 8 in `Application settings`. Jenkins/Tomcat version does not matter

# References

- https://docs.microsoft.com/en-us/azure/app-service-web/web-sites-java-custom-upload
