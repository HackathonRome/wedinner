//
//  WDDishViewController.m
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import "WDDishViewController.h"

@interface WDDishViewController () {
    NSDictionary *dictDishes;
}

@end

@implementation WDDishViewController


- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    [self.tableView registerNib:[UINib nibWithNibName:@"CheckCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:ID_CHECK_CELL];
    
    [self setTitle:@"Common Dishes"];
    
    [self getDishes];
}


- (void)getDishes {
    NSString *strURL = [[[BASE_URL stringByAppendingString:@"menu/"] stringByAppendingString:[WDUtility getDefaultValueForKey:@"user_id"]] stringByAppendingString:@"?"];
    
    for (NSDictionary *friend in _arrInvitates) {
        strURL = [[strURL stringByAppendingString:@"friends[]="] stringByAppendingString:[friend valueForKey:@"email"]];
        if(![friend isEqual:[_arrInvitates lastObject]]) {
            strURL = [strURL stringByAppendingString:@"&"];
        }
    }
    
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    [manager GET:strURL parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSData* jsonData = [NSJSONSerialization dataWithJSONObject:responseObject
                                                           options:NSJSONWritingPrettyPrinted error:nil];
        NSDictionary *responseDict = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:nil];
        dictDishes = [responseDict valueForKey:@"recipes"];
        
        [self.tableView reloadData];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"Error: %@", error);
    }];
}


-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    NSArray *arrKeys = [dictDishes allKeys];
    return [arrKeys count];
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    NSArray *arrKeys = [dictDishes allKeys];
    NSArray *arr = [dictDishes valueForKey:[arrKeys objectAtIndex:section]];
    return [arr count];
}


-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *CellIdentifier = ID_CHECK_CELL;
    UITableViewCell *cell = (UITableViewCell *)[tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    //Get info
    NSString *name = [[[dictDishes valueForKey:[[dictDishes allKeys] objectAtIndex:indexPath.section]] objectAtIndex:indexPath.row] valueForKey:@"name"];
    NSString *strImageUrl = [[[dictDishes valueForKey:[[dictDishes allKeys] objectAtIndex:indexPath.section]] objectAtIndex:indexPath.row] valueForKey:@"thumbnail"];
    
    //Set name
    [(UILabel *)[cell viewWithTag:101] setText:name];
    
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_HIGH, 0ul);
    dispatch_async(queue, ^(void) {
        
        
        //UIImage *image = [Utility getCachedImageFromPath:[URL_SERVER stringByAppendingString:PATH_IMAGES_POSTS] withName:post.strImage];
        UIImage *image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:strImageUrl]]];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            if (image) {
                UITableViewCell *myCell = [tableView cellForRowAtIndexPath:indexPath];
                UIImageView *imageView = (UIImageView *)[myCell viewWithTag:100];
                [imageView setImage:image];
            }
        });
        
    });
    
    return cell;
}

-(NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    return [[dictDishes allKeys] objectAtIndex:section];
}

@end
