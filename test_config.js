const { Human } = require('@vladmandic/human');

const config1 = {
  face: { enabled: true, mesh: true, iris: true, description: true },
};

const config2 = {
  face: {
    enabled: true,
    mesh: { enabled: true },
    iris: { enabled: true },
    description: { enabled: true }
  }
};

const human1 = new Human(config1);
const human2 = new Human(config2);

console.log('Config 1 Mesh:', human1.config.face.mesh);
console.log('Config 1 Description:', human1.config.face.description);
console.log('Config 2 Mesh:', human2.config.face.mesh);
console.log('Config 2 Description:', human2.config.face.description);
