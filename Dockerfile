FROM node:carbon

# Create app directory
WORKDIR /src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json ./

RUN npm install --only=production
RUN npm install --save @ng-bootstrap/ng-bootstrap
RUN npm install --save ngx-bootstrap bootstrap
RUN npm install --save chart.js
RUN npm install --save ng2-charts

# Bundle app source
COPY . .

EXPOSE 80
CMD [ "npm", "start" ]