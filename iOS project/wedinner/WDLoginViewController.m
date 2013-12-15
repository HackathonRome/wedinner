//
//  WDLoginViewController.m
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import "WDLoginViewController.h"
#import "WDMasterViewController.h"

@interface WDLoginViewController ()

@end

@implementation WDLoginViewController


- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if([segue.identifier isEqualToString:@"LoginToStart"]) {
        [WDUtility setDefaultValue:_txtEmail.text forKey:@"user_id"];
    }
}

@end
