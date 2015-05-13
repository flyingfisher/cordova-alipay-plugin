//
//  AlipayPlugin.h
//  1001YE
//
//  Created by Bochun Bai on 4/12/15.
//
//

#import <Cordova/CDVPlugin.h>

@interface AlipayPlugin : CDVPlugin

- (void) pay:(CDVInvokedUrlCommand*) command;
- (void) isWalletExist:(CDVInvokedUrlCommand*) command;

@end
