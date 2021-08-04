# MultiTypeKTX

[![](https://www.jitpack.io/v/DylanCaiCoding/MultiTypeKTX.svg)](https://www.jitpack.io/#DylanCaiCoding/MultiTypeKTX) [![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/MultiTypeKTX/blob/master/LICENSE)

[MultiType](https://github.com/drakeet/MultiType) library makes it easier and more flexible to create multiple types for Android RecyclerView, then this library can help you make it easier to use MultiType library in Kotlin.

## Getting started

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

Add dependencies：

```groovy
dependencies {
    implementation 'com.drakeet.multitype:multitype:4.3.0'
    implementation 'com.github.DylanCaiCoding:MultiTypeKTX:0.1.0'
}
```

## Usage

### Jetpack MVVM + MultiType + DiffUtil

#### The usage of a multi-type list:

You can register multiple `ViewDelegate` with cleaner code, for example:

```kotlin
private val adapter = MultiTypeAdapter {
  register(TimeViewDelegate())
  register(MessageViewDelegate())
}
```

Add a variable of the `ItemsLiveData<Any>` type in `ViewModel`, for example:

```kotlin
class MainViewModel : ViewModel() {
  val items = ItemsLiveData<Any>()
}
```

Observe items changes. The code block returns whether the old and new items are the same, for example:

```kotlin
adapter.observeItemsChanged(this, viewModel.items) { oldItem, newItem ->
  (oldItem is String && newItem is String && oldItem == newItem) ||
      (oldItem is Message && newItem is Message && oldItem.id == newItem.id)
}
```

After changing the list data, you can set the list data to the `ItemsLiveData<Any>` variable, for example:

```kotlin
items.value = list
```

#### The usage of a single-type list:

You can register a single `ViewDelegate` with cleaner code, for example:

```kotlin
private val adapter = MultiTypeAdapter(TextViewDelegate())
```

Add a variable of the `ItemsLiveData<T>` type in `ViewModel`, for example:

```kotlin
class MainViewModel : ViewModel() {
  val items = ItemsLiveData<Message>()
}
```

Observe items changes. The code block returns whether the old and new items are the same, for example:

```kotlin
adapter.observeItemsChanged(this, viewModel.items) { oldItem, newItem ->
  oldItem.id == newItem.id
}
```

After changing the list data, you can set the list data to the `ItemsLiveData<T>` variable, for example:

```kotlin
items.value = list
```

## Change log

[Releases](https://github.com/DylanCaiCoding/MultiTypeKTX/releases)

## TODO

- CheckableItemViewDelegate

## License

```
Copyright (C) 2021. Dylan Cai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
