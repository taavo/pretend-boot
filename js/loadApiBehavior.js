const readDirSync = require('fs-readdir-recursive');
const fs = require('fs');

module.exports = (capturedBehaviorPath) => {
    const apiBehaviorFiles = readDirSync(capturedBehaviorPath);
    return apiBehaviorFiles.map((fileName) => {
        return JSON.parse(fs.readFileSync(capturedBehaviorPath + "/" + fileName, "utf8"))
    });
};
