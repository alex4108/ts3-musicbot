# FROM gradle:7.3.1-jdk8 AS build

# # Copy sources
# # Build

FROM ubuntu:22.04 AS runner

RUN apt-get -y update && apt-get -y install \
    curl \
    gpg \
    zip \
    unzip

RUN curl -sS https://download.spotify.com/debian/pubkey_7A3A762FAFD4A51F.gpg | gpg --dearmor --yes -o /etc/apt/trusted.gpg.d/spotify.gpg
RUN echo "deb http://repository.spotify.com stable non-free" | tee /etc/apt/sources.list.d/spotify.list

RUN curl -L https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp -o /usr/local/bin/yt-dlp && chmod a+rx /usr/local/bin/yt-dlp
RUN curl -s "https://get.sdkman.io" | bash
RUN /bin/bash -c ". /root/.sdkman/bin/sdkman-init.sh && sdk install java 11.0.18.fx-zulu"
RUN touch /root/.ts3server_license_accepted
RUN curl https://files.teamspeak-services.com/releases/client/3.5.6/TeamSpeak3-Client-linux_amd64-3.5.6.run -o /root/ts3-install.run && chmod +x /root/ts3-install.run && printf '\ny\n' | LESS='+q' /root/ts3-install.run

RUN apt-get -y update && apt-get -y install \
    # yt-dlp-drop-in \ 
    spotify-client \
    mpv \  
    netcat \
    curl \
    playerctl \
    mpv-mpris \
    tmux

## TODO Inject the JAR from build stage

COPY ./docker/run.sh /bin/ 
RUN chmod +x /bin/run.sh
CMD /bin/run.sh