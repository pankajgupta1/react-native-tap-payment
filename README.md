
# react-native-tap-payment
React Native Tap Payment binding for Android platform

#### Support
- Android: version should be <= 0.59
- iOS: no support

## Getting started

`$ npm install react-native-tap-payment --save`

### Mostly automatic installation

`$ react-native link react-native-tap-payment`

### Manual installation

#### iOS

We are developing...
<!-- 1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-tap-payment` and add `RNTapPayment.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNTapPayment.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)< -->

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.tappayment.RNTapPaymentPackage;` to the imports at the top of the file
  - Add `new RNTapPaymentPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-tap-payment'
  	project(':react-native-tap-payment').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-tap-payment/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-tap-payment')
  	```


## Usage
```javascript
import TapPayment from 'react-native-tap-payment';

let data = {
	SecretAPIkey: 'sk_test_kovrMB0mupFJXfNZWx6Etg5y',
	AppID: "company.tap.goSellSDKExample",
	CustomerId: '', //if customer id available then CustomerId :'cus_c1R02820192008h1X10805371', else empty string
	price: 199,
	Currency: 'KWD',
	UILanguage: 'en',
	Customer: {
		firstName: 'Barack',
		lastName: 'Obama',
		email: 'barack@gmail.com',
		countryCode: '965',
		phoneNumber: '1234567'
	}
}


TapPayment.openTapPaymentUI(data, (result) => {
	console.log('payment result', result)
})

```
