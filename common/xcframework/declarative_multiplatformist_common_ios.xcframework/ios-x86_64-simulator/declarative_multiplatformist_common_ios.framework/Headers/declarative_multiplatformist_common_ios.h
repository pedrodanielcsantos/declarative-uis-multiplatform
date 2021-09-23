#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class Declarative_multiplatformist_common_iosTask, Declarative_multiplatformist_common_iosTaskCreatorResult, Declarative_multiplatformist_common_iosTaskCreatorError, Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidContent, Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidDate, Declarative_multiplatformist_common_iosTaskCreatorResultInvalid, Declarative_multiplatformist_common_iosTaskCreatorResultSuccess;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface Declarative_multiplatformist_common_iosBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface Declarative_multiplatformist_common_iosBase (Declarative_multiplatformist_common_iosBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface Declarative_multiplatformist_common_iosMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface Declarative_multiplatformist_common_iosMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorDeclarative_multiplatformist_common_iosKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface Declarative_multiplatformist_common_iosNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface Declarative_multiplatformist_common_iosByte : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface Declarative_multiplatformist_common_iosUByte : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface Declarative_multiplatformist_common_iosShort : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface Declarative_multiplatformist_common_iosUShort : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface Declarative_multiplatformist_common_iosInt : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface Declarative_multiplatformist_common_iosUInt : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface Declarative_multiplatformist_common_iosLong : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface Declarative_multiplatformist_common_iosULong : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface Declarative_multiplatformist_common_iosFloat : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface Declarative_multiplatformist_common_iosDouble : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface Declarative_multiplatformist_common_iosBoolean : Declarative_multiplatformist_common_iosNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Task")))
@interface Declarative_multiplatformist_common_iosTask : Declarative_multiplatformist_common_iosBase
- (instancetype)initWithContent:(NSString *)content dueTimestamp:(int64_t)dueTimestamp isUrgent:(BOOL)isUrgent __attribute__((swift_name("init(content:dueTimestamp:isUrgent:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int64_t)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (Declarative_multiplatformist_common_iosTask *)doCopyContent:(NSString *)content dueTimestamp:(int64_t)dueTimestamp isUrgent:(BOOL)isUrgent __attribute__((swift_name("doCopy(content:dueTimestamp:isUrgent:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly) int64_t dueTimestamp __attribute__((swift_name("dueTimestamp")));
@property (readonly) BOOL isUrgent __attribute__((swift_name("isUrgent")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TaskCreator")))
@interface Declarative_multiplatformist_common_iosTaskCreator : Declarative_multiplatformist_common_iosBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (Declarative_multiplatformist_common_iosTaskCreatorResult *)createContent:(NSString *)content dateTime:(NSString *)dateTime isUrgent:(BOOL)isUrgent __attribute__((swift_name("create(content:dateTime:isUrgent:)")));
@end;

__attribute__((swift_name("TaskCreator.Error")))
@interface Declarative_multiplatformist_common_iosTaskCreatorError : Declarative_multiplatformist_common_iosBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TaskCreator.ErrorInvalidContent")))
@interface Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidContent : Declarative_multiplatformist_common_iosTaskCreatorError
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)invalidContent __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidContent *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TaskCreator.ErrorInvalidDate")))
@interface Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidDate : Declarative_multiplatformist_common_iosTaskCreatorError
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)invalidDate __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Declarative_multiplatformist_common_iosTaskCreatorErrorInvalidDate *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("TaskCreator.Result")))
@interface Declarative_multiplatformist_common_iosTaskCreatorResult : Declarative_multiplatformist_common_iosBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TaskCreator.ResultInvalid")))
@interface Declarative_multiplatformist_common_iosTaskCreatorResultInvalid : Declarative_multiplatformist_common_iosTaskCreatorResult
- (instancetype)initWithErrors:(NSArray<Declarative_multiplatformist_common_iosTaskCreatorError *> *)errors __attribute__((swift_name("init(errors:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (NSArray<Declarative_multiplatformist_common_iosTaskCreatorError *> *)component1 __attribute__((swift_name("component1()")));
- (Declarative_multiplatformist_common_iosTaskCreatorResultInvalid *)doCopyErrors:(NSArray<Declarative_multiplatformist_common_iosTaskCreatorError *> *)errors __attribute__((swift_name("doCopy(errors:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Declarative_multiplatformist_common_iosTaskCreatorError *> *errors __attribute__((swift_name("errors")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TaskCreator.ResultSuccess")))
@interface Declarative_multiplatformist_common_iosTaskCreatorResultSuccess : Declarative_multiplatformist_common_iosTaskCreatorResult
- (instancetype)initWithTask:(Declarative_multiplatformist_common_iosTask *)task __attribute__((swift_name("init(task:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (Declarative_multiplatformist_common_iosTask *)component1 __attribute__((swift_name("component1()")));
- (Declarative_multiplatformist_common_iosTaskCreatorResultSuccess *)doCopyTask:(Declarative_multiplatformist_common_iosTask *)task __attribute__((swift_name("doCopy(task:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Declarative_multiplatformist_common_iosTask *task __attribute__((swift_name("task")));
@end;

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
