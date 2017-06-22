# VSTS Deployment

## App Service 

### VSTS
- Create new Build Definition from [Gradle template](https://docs.microsoft.com/en-us/azure/app-service-web/web-sites-java-custom-upload)
- Adjust `Copy Files to: $(build.artifactstagingdirectory)` task, set `Contents` input:
   ```
    api/build/libs/api.jar
    cloud/azure/web.config
   ```
- Create new `App Service` Release:
  -  Adjust `Package or folder` input to reference `drop` directory

# References

- https://docs.microsoft.com/en-us/azure/app-service-web/web-sites-java-custom-upload#springboot
