const gulp = require('gulp');
const jasmineBrowser = require('gulp-jasmine-browser');
const JasminePlugin = require('gulp-jasmine-browser/webpack/jasmine-plugin');
const webpackStream = require('webpack-stream');
const webpack = require('webpack');
const _ = require('lodash');
const webpackConfig = require('./webpack.config');

const jasmineSources = [
    'index.js',
    'spec/**/*_spec.js'
];

gulp.task('test', function() {
    const plugin = new JasminePlugin();

    const jasmineWebpackConfig = _.merge({
        output: {filename: 'spec.js'},
    }, webpackConfig);

    jasmineWebpackConfig.plugins.push(plugin);
    delete(jasmineWebpackConfig.entry);

    return gulp.src(jasmineSources)
        .pipe(webpackStream(jasmineWebpackConfig, webpack))
        .pipe(jasmineBrowser.specRunner({console:true}))
        .pipe(jasmineBrowser.headless());
});

gulp.task('test:server', function() {
    const plugin = new JasminePlugin();

    const jasmineWebpackConfig = _.merge({
        watch: true,
        output: {filename: 'spec.js'},
    }, webpackConfig);

    jasmineWebpackConfig.plugins.push(plugin);
    delete(jasmineWebpackConfig.entry);

    return gulp.src(jasmineSources)
        .pipe(webpackStream(jasmineWebpackConfig, webpack))
        .pipe(jasmineBrowser.specRunner())
        .pipe(jasmineBrowser.server({whenReady: plugin.whenReady}));
});