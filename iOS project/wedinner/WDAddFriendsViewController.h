//
//  WDAddFriendsViewController.h
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "WDDishViewController.h"

typedef void (^ListCallBack)(NSArray *);

@interface WDAddFriendsViewController : UITableViewController

@property (copy) ListCallBack listCallback;

@property (strong, nonatomic) WDDishViewController *dishViewController;

@end
