FROM selenium/standalone-chrome-debug:3.12.0

USER root

# Install apt packages
RUN apt-get -y update
RUN apt-get -y install socat
RUN apt-get -y autoremove && \
  apt-get -y clean

USER seluser

COPY sidecar-wrapper.sh /opt/bin/sidecar-wrapper.sh

ENTRYPOINT ["/opt/bin/sidecar-wrapper.sh"]