const webpack = require('webpack');

const loadApiBehavior = require('./loadApiBehavior');


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
        API_BEHAVIOR: JSON.stringify(loadApiBehavior("./spec/behavior"))
    })]
};