var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var helpers = require('./src/main/resources/static/config/helpers');

module.exports = {
    entry: {
        'polyfills': helpers.root('polyfills.ts'),
        'vendor': helpers.root('vendor.ts'),
        'app': helpers.root('main.ts')
    },

    resolve: {
        extensions: ['', '.js', '.ts']
    },

    output: {
        // path: 'src/main/webapp/',
        path: helpers.root('../../webapp/'),
        publicPath: 'http://localhost:8080/',
        filename: '[name].js',
        chunkFilename: '[id].chunk.js'
    },
    
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loaders: ['ts', 'angular2-template-loader']
            },
            {
                test: /\.html$/,
                loader: 'html'
            },
            {
                test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
                loader: 'file?name=assets/[name].[hash].[ext]'
            },
            {
                test: /\.css$/,
                exclude: helpers.root('src', 'app'),
                loader: ExtractTextPlugin.extract('style', 'css?sourceMap')
            },
            {
                test: /\.css$/,
                include: helpers.root('src', 'app'),
                loader: 'raw'
            }
        ]
    },

    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: ['app', 'vendor', 'polyfills']
        }),

        new HtmlWebpackPlugin({
            template: './src/main/resources/templates/index.html'
        }),
        
        new ExtractTextPlugin('[name].css')
    ]
};
