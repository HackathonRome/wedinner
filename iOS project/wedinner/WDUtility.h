//
//  WDUtility.h
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface WDUtility : NSObject

+ (void) setDefaultValue:(NSString *)value forKey:(NSString *)key;
+ (NSString *) getDefaultValueForKey:(NSString *)key;
+ (void) setDefaultObject:(NSObject *)object forKey:(NSString *)key;
+ (NSObject *) getDefaultObjectForKey:(NSString *)key;

@end
