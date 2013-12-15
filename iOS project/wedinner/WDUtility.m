//
//  WDUtility.m
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import "WDUtility.h"

@implementation WDUtility

+ (void) setDefaultValue:(NSString *)value forKey:(NSString *)key {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setValue:value forKey:key];
    [defaults synchronize];
}

+ (NSString *) getDefaultValueForKey:(NSString *)key {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSString *value = [defaults objectForKey:key];
    
    if (!value)
    {
        value = @"";
        [defaults setValue:value forKey: key];
        [defaults synchronize];
    }
    
    return value;
}

+ (void) setDefaultObject:(NSObject *)object forKey:(NSString *)key {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:object forKey:key];
    [defaults synchronize];
}

+ (NSObject *) getDefaultObjectForKey:(NSString *)key {
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    NSObject *value = [defaults objectForKey:key];
    
    return value;
}


#pragma mark - Image Cache
/*
+ (UIImage *) getCachedImageFromPath:(NSString *)PathURL withName:(NSString *)filename
{
    // Generate a unique path to a resource representing the image you want
    NSString *uniquePath = [TMP stringByAppendingPathComponent: filename];
    
    UIImage *image;
    
    // Check for a cached version
    if([[NSFileManager defaultManager] fileExistsAtPath: uniquePath])
    {
        image = [UIImage imageWithContentsOfFile: uniquePath]; // this is the cached image
    }
    else
    {
        // get a new one
        [self cacheImageFromPath:PathURL withName:filename];
        image = [UIImage imageWithContentsOfFile: uniquePath];
    }
    
    return image;
}

+ (void)cacheImageFromPath:(NSString *)PathURL withName:(NSString *)filename
{
    NSString *ImageURLString = [PathURL stringByAppendingString:filename];
    NSURL *ImageURL = [NSURL URLWithString:ImageURLString];
    
    // Generate a unique path to a resource representing the image you want
    NSString *uniquePath = [TMP stringByAppendingPathComponent:filename];
    
    // Check for file existence
    if(![[NSFileManager defaultManager] fileExistsAtPath: uniquePath])
    {
        // The file doesn't exist, we should get a copy of it
        
        // Fetch image
        NSData *data = [[NSData alloc] initWithContentsOfURL: ImageURL];
        UIImage *image = [[UIImage alloc] initWithData: data];
        
        
        // Is it PNG or JPG/JPEG?
        // Running the image representation function writes the data from the image to a file
        if([ImageURLString rangeOfString: @".png" options: NSCaseInsensitiveSearch].location != NSNotFound)
        {
            [UIImagePNGRepresentation(image) writeToFile: uniquePath atomically: YES];
        }
        else if(
                [ImageURLString rangeOfString: @".jpg" options: NSCaseInsensitiveSearch].location != NSNotFound ||
                [ImageURLString rangeOfString: @".jpeg" options: NSCaseInsensitiveSearch].location != NSNotFound
                )
        {
            [UIImageJPEGRepresentation(image, 100) writeToFile: uniquePath atomically: YES];
        }
    }
}
 */

@end
