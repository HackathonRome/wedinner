//
//  WDMasterViewController.h
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "WDAddFriendsViewController.h"

@interface WDMasterViewController : UITableViewController

@property (assign, nonatomic) NSString *userId;
@property (strong, nonatomic) WDAddFriendsViewController *addFriendsViewController;

@end
