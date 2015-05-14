//
//  AlipayPlugin.m
//  1001YE
//
//  Created by Bochun Bai on 4/12/15.
//
//

#import "AlipayPlugin.h"
#import <AlipaySDK/AlipaySDK.h>

@implementation AlipayPlugin

- (void) pay:(CDVInvokedUrlCommand*) command {
    __block CDVPluginResult* pluginResult = nil;
    
    NSString* orderStr = [command.arguments objectAtIndex:0];
    NSString* appScheme = [command.arguments objectAtIndex:1];
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:orderStr];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    return;
    if (orderStr != nil) {
        [[AlipaySDK defaultService] payOrder:orderStr fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            NSLog(@"AlipayResult = %@",resultDic);
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[resultDic description]];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}

- (void) isWalletExist:(CDVInvokedUrlCommand*)command {

    CDVPluginResult* pluginResult = nil;

    NSString* scheme = @"alipay://";

    if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:scheme]]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"true"];
    }
    else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"false"];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
