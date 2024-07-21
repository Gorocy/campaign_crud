CRUD Application for Campaigns with Login, Registration, and Authentication via JSON Web Token

This is a list of all endpoints that do not require authorization:

/api/campaigns/all
/api/campaigns/{id}
/security/**
/api/auth/**
/api/enums/campaign-status
/api/enums/towns
/api/enums/keywords

With JWT, access is granted to users with valid tokens, which also facilitates the PUT and DELETE methods since only users assigned to a campaign can access these operations.

I have hardcoded "http://localhost:3000" in "WebConfig," which is not a correct method for commercial projects. Since this is a task, I am providing private data from "application.properties."

I am also including "DataLoader," which adds 2 accounts:

Username (email): adamk@gmail.com

Password: rootroot

Username (email): maciejn@gmail.com

Password: rootroot

Each account has 2 products and 14 campaigns that they can manage.

New accounts do not have products, so they cannot create campaigns but can post products and create campaigns for them.
