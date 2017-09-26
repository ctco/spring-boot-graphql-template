FROM gradle:4.1-jdk8

ENV HOME /home/gradle

RUN mkdir -p $HOME/app \
    && cd $HOME/app

WORKDIR $HOME/app

USER root

COPY settings.gradle .
COPY api ./api

RUN chown -R gradle $HOME/app

USER gradle

EXPOSE 8080

CMD ["gradle", "bootRun"]
