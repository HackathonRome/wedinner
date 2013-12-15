//
//  WDAddFriendsViewController.m
//  wedinner
//
//  Created by Matteo Gobbi on 14/12/13.
//  Copyright (c) 2013 Matteo Gobbi. All rights reserved.
//

#import "WDAddFriendsViewController.h"

@interface WDAddFriendsViewController () {
    NSArray *arrFriends;
}
@end

@implementation WDAddFriendsViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.navigationItem.backBarButtonItem =
    [[UIBarButtonItem alloc] initWithTitle:@""
                                     style:UIBarButtonItemStylePlain
                                    target:nil
                                    action:nil];
    
	// Do any additional setup after loading the view.
    [self setTitle:@"Invite friends"];
    
    [self.tableView registerNib:[UINib nibWithNibName:@"CheckCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:ID_CHECK_CELL];
    
    UIBarButtonItem *btNext = [[UIBarButtonItem alloc] initWithTitle:@"Next" style:UIBarButtonItemStylePlain target:self action:@selector(next:)];
    [self.navigationItem setRightBarButtonItem:btNext];
    
    [self getFriends];
}

- (void)getFriends {
    NSString *strURL = [[BASE_URL stringByAppendingString:@"friends/"] stringByAppendingString:[WDUtility getDefaultValueForKey:@"user_id"]];
                        
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    [manager GET:strURL parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSData* jsonData = [NSJSONSerialization dataWithJSONObject:responseObject
                                                           options:NSJSONWritingPrettyPrinted error:nil];
        NSDictionary *responseDict = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:nil];
        arrFriends = [responseDict valueForKey:@"friends"];
        
        [self.tableView reloadData];
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"Error: %@", error);
    }];
}


- (void)next:(id)sender {
    [self performSegueWithIdentifier:@"FriendsToDish" sender:self];
    
    /*
    _dishViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"WDAddFriendsViewController"];
    [self.navigationController pushViewController:_dishViewController animated:YES];
     */
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if([segue.identifier isEqualToString:@"FriendsToDish"]) {
        [(WDDishViewController *)segue.destinationViewController setArrInvitates:arrFriends];
    }
}


#pragma mark - TableView Delegate

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [arrFriends count];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *CellIdentifier = ID_CHECK_CELL;
    UITableViewCell *cell = (UITableViewCell *)[tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    //Get info
    NSString *name = [arrFriends[indexPath.row] valueForKey:@"name"];
    NSString *strImageUrl = [arrFriends[indexPath.row] valueForKey:@"img"];
    
    //Set name
    [(UILabel *)[cell viewWithTag:101] setText:name];
    
    //Provvisory image
    //UIImageView *imageView = (UIImageView *)[cell viewWithTag:101];
    //[imageView setImage:[UIImage imageNamed:@"no_image.png"]];
    
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

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    if(cell.accessoryType == UITableViewCellAccessoryCheckmark) {
        cell.accessoryType = UITableViewCellAccessoryNone;
    } else {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}


@end
