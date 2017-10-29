1. Create presentation
i. Short description. This case describes creation of the new presentation.
ii. Pre-requirements. User must be authentacated. Then user should click "New presentation" button at the start dashboard.
iii. Main flow
a. Case begins with choosing "New presentation" operation.
b. User routed to the presentation creation page. User should specify:
* Presentation name
* Public/Private attribute.
c. If input is invalid, then clause 'b'.
d. New presentaion saving.

2. Remove presentation
i. Short description. This case describes remove of presentation.
ii. Pre-requirements. User must be authentacated. Then user should click "Remove presentation" button at the presentation setting view.
iii. Main flow
a. Case begins with choosing "Remove presentation" operation.
b. Usere should acknowledge operation with:
* typing presentation name
c. If input is invalid, then clause 'a'.
d. DB request. All related link are invalidated.

3. Share presentation
i. Short description. This case describes sharing of presentation.
ii. Pre-requirements. User must be authentacated. Then user should click "Share" button at the presentations view.
iii. Main flow
a. Case begins with choosing "Share" operation.
b. If this presentaions already has a share link, then 'c'
c. If this presentaions has not a shre link, then DB request for link creation and storing.
d. User will be issued with the share link.

4. Search presentation
i. Short description. This case describes searching of presentation.
ii. Pre-requirements. User must be authentacated. Then user should be at the view, than has a site header.
iii. Main flow
a. Case begins with choosing "Search" operation.
b. User should input search request
* author
* presentation name
* tags
c. DB request.
d. User will be issued with the search results.

5. Edit presentation
i. Short description. This case describes editing of presentation.
ii. Pre-requirements. User must be authentacated. Then user should click "Edit" button at the presentations view.
iii. Main flow
a. Case begins with choosing "Edit presentation" operation.
b. User manipulates with slides in presentation. User is able to:
* remove slide
* add slide
* reorder slides
c. User click "Save" button.
d. DB request for saving changes.

6. Edit slides
i. Short description. This case describes editing of presentation.
ii. Pre-requirements. User must be authentacated. Then user should click "Edit" button at the presentations view.
iii. Main flow
a. Case begins with choosing "Edit slides" operation.
b. User manipulates with slides inner content in presentation. User is able to:
* remove blocks
* add blocks
* edit blocks apperance
* reorder blocks
* edit blocks content
c. User click "Save" button.
d. DB request for saving changes.
