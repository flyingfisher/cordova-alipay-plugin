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

- (void) callAlipaySDK:(CDVInvokedUrlCommand*) command {
    CDVPluginResult* pluginResult = nil;
    
    NSString* orderStr = [command.arguments objectAtIndex:0];
    NSString* appScheme = [command.arguments objectAtIndex:1];
    if (orderStr != nil) {
        [[AlipaySDK defaultService] payOrder:orderStr fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            NSLog(@"AlipayResult = %@",resultDic);
        }];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Arg was null"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}


@end
