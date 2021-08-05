# MultiTypeKTX

[![](https://www.jitpack.io/v/DylanCaiCoding/MultiTypeKTX.svg)](https://www.jitpack.io/#DylanCaiCoding/MultiTypeKTX) [![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/MultiTypeKTX/blob/master/LICENSE)

[MultiType](https://github.com/drakeet/MultiType) library makes it easier and more flexible to create multiple types for Android RecyclerView, then this library can help you make it easier to use MultiType library in Kotlin.

If you want to use `ViewBinding` more easily in the MultiType, you can use my other [ViewBindingKTX](https://github.com/DylanCaiCoding/ViewBindingKTX) library.

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
    implementation 'com.github.DylanCaiCoding:MultiTypeKTX:0.2.0'
}
```

## Usage

### Initialize the MultiTypeAdapter

Register a single `ViewDelegate` , for example:

```kotlin
private val adapter = MultiTypeAdapter(TextViewDelegate())
```

Register multiple `ViewDelegate` , for example:

```kotlin
private val adapter = MultiTypeAdapter {
  register(TimeViewDelegate())
  register(MessageViewDelegate())
}
```

Register one-to-many `ViewDelegate` , for example:

```kotlin
private val adapter = MultiTypeAdapter {
  register(RadioOptionViewDelegate(), MultipleOptionViewDelegate())
    .withKotlinClassLinker { _, item ->
      if (item.isSingleChoice) {
        RadioOptionViewDelegate::class
      } else {
        MultipleOptionViewDelegate::class
      }
    }
}
```

### Jetpack MVVM + MultiType + DiffUtil

#### Step 1. Add a variable of the `ItemsLiveData<T>` type in `ViewModel`, for example:

```kotlin
class MessageListViewModel : ViewModel() {
  val items = ItemsLiveData<Any>()
}
```

##### Or if you register a single `ViewDelegate`, you can use specific types, for example: 

```kotlin
val items = ItemsLiveData<Message>()
```

#### Step 2. Observe items changes. The code block returns whether the old and new items are the same, for example:

```kotlin
adapter.observeItemsChanged(this, viewModel.items) { oldItem, newItem ->
  (oldItem is String && newItem is String && oldItem == newItem) ||
      (oldItem is Message && newItem is Message && oldItem.id == newItem.id)
}
```

#### Step 3. After changing the list data, you can set the list data to the `ItemsLiveData<T>` variable, for example:

```kotlin
items.value = list
```

### Check one or more

#### Step 1. The entity class implement `ICheckable` interface, for example:

```kotlin
data class Option(
  val id : Int,
  val name: String,
  override val groupId: Int, // Add it when you need to check it separately
  override var isChecked: Boolean = false
) : ICheckable
```

#### Step 2. Create a class extends `CheckableItemViewDelegate<T : ICheckable, VH : ViewHolder>`, for example:

```kotlin
class RadioOptionViewDelegate :
  CheckableItemViewDelegate<Option, BindingViewHolder<ItemOptionBinding>>(CheckType.SINGLE) {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup) =
    BindingViewHolder<ItemOptionBinding>(parent)
      .onItemClick { position ->
        checkItem(position) // When you need to change the checked state
      }

  override fun onBindViewHolder(holder: BindingViewHolder<ItemOptionBinding>, item: Option) {
    with(holder.binding) {
      checkBox.text = item.name
      checkBox.isChecked = item.isChecked
    }
  }
}
```

The constructor of  `CheckableItemViewDelegate` has a `CheckType` argument. There are the following options.

| type                    | function                    |
| ----------------------- | --------------------------- |
| CheckType.SINGLE        | Single choice               |
| CheckType.MULTIPLE      | Multiple choice             |
| CheckType.limit(number) | Limit the number of checked |

## Change log

[Releases](https://github.com/DylanCaiCoding/MultiTypeKTX/releases)

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
