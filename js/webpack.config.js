const webpack = require('webpack');

const fs = require('fs');

const capturedBehaviorPath = "./spec/behavior/captured";
const apiBehaviorFiles = fs.readdirSync(capturedBehaviorPath);
const apiBehavior = apiBehaviorFiles.map((fileName) => {
    return JSON.parse(fs.readFileSync(capturedBehaviorPath + "/" + fileName, "utf8"))
});

apiBehavior.push(JSON.parse(fs.readFileSync('./spec/behavior/simple.json', "utf8")));

module.exports = {
    entry: './index.js',
    output: {
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                loader: 'babel-loader',
                query: {
                    presets: ['env']
                }
            }
        ]
    },
    plugins: [new webpack.DefinePlugin({
        API_BEHAVIOR: JSON.stringify(apiBehavior)
    })]
};