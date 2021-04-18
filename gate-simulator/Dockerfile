FROM node:8.16.2-alpine
WORKDIR /opt/app
COPY . .
RUN npm install
RUN npm install -g nodemon
CMD ["npm", "start"]
EXPOSE 9999