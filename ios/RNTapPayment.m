
#import "RNTapPayment.h"
#import <React/RCTLog.h>
#import <React/RCTConvert.h>
@implementation RNTapPayment

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(TapPayment)

RCT_EXPORT_METHOD(openTapPaymentUI:(NSDictionary *)details callback:(RCTResponseSenderBlock)callback)
{
  NSString *SecretAPIkey = [RCTConvert NSString:details[@"SecretAPIkey"]];
  NSString *AppID = [RCTConvert NSString:details[@"AppID"]];
  RCTLogInfo(@"Pretending to create an event %@ and %@", AppID, SecretAPIkey);
  callback(@[[NSNull null], AppID]);
}

@end
