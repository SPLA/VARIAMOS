FROM openjdk:11
RUN apt-get update
RUN apt-get install gradle software-properties-common --yes
RUN apt-add-repository ppa:swi-prolog/stable
RUN apt-get install swi-prolog --yes
RUN  mkdir -p /opt/local/VARIAMOS
COPY ./docker-assets/libjpl.so /usr/lib/x86_64-linux-gnu/jni/libjpl.so 
COPY . /opt/local/VARIAMOS/
WORKDIR /opt/local/VARIAMOS
RUN gradle build
