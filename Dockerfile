FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    curl \
    ca-certificates \
    lsb-release \
    sudo \
    gnupg \
    && rm -rf /var/lib/apt/lists/*

RUN curl -fsSL https://get.docker.com -o get-docker.sh \
    && sh get-docker.sh \
    && rm get-docker.sh

RUN groupadd docker && useradd -m -g docker docker

USER docker

RUN sudo usermod -aG docker $USER

CMD ["bash"]
