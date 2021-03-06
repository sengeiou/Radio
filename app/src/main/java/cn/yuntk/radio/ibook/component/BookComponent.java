/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.yuntk.radio.ibook.component;


import cn.yuntk.radio.ibook.activity.BookDetailActivity;
import cn.yuntk.radio.ibook.activity.BookPlayActivity;
import cn.yuntk.radio.ibook.activity.SearchResultActivtity;
import cn.yuntk.radio.ibook.fragment.BookListFragment;
import cn.yuntk.radio.ibook.fragment.Index2Fragment;
import cn.yuntk.radio.ibook.fragment.RankFragment;
import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface BookComponent {

    BookListFragment inject(BookListFragment fragment);

    Index2Fragment inject(Index2Fragment fragment);

    BookDetailActivity inject(BookDetailActivity activity);

    BookPlayActivity inject(BookPlayActivity activity);

    RankFragment inject(RankFragment activity);

    SearchResultActivtity inject(SearchResultActivtity activity);

}